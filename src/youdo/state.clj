(ns youdo.state
  (:require [youdo.db :as db]))

(defn list-tasks
  "Given a file path, returns list of tasks"
  [path]
  (let [state (db/read-content path)
        tasks-by-order (:tasks-by-order state)
        tasks-to-filter (map #(get-in state [:tasks-by-id %]) tasks-by-order)
        tasks-to-display (filter #(not (:done? %)) tasks-to-filter)]
    (map :task-name tasks-to-display)))

(defn create-task
  [task-name]
  {:task-id (java.util.UUID/randomUUID)
   :done? false
   :task-name task-name})

(defn update-state-at-path
  "Calls `f` on state at given path and saves the result to the same file."
  [path f]
  (let [state (db/read-content path)]
    (db/save! path (f state))))

(defn add-task
  "Given a file path, adds task to the end of the tasks list."
  [path task-name]
  (let [task (create-task task-name)
        task-id (:task-id task)]
    (update-state-at-path path
                          (fn [state]
                            (-> state
                                (update :tasks-by-order (fn [tasks-by-order]
                                                          (vec (conj tasks-by-order task-id))))
                                (assoc-in [:tasks-by-id task-id] task))))))

(defn set-done?
  [path task-id done?]
  (update-state-at-path path
                        (fn [state]
                          (assoc-in state [:tasks-by-id task-id :done?] done?))))