(ns youdo.state
  (:require [youdo.db :as db]))

(defn list-tasks
  "Given a file path, returns list of tasks."
  [path]
  (map :task-name (db/read-content path)))

(defn create-task
  [task-name]
  {:task-id (java.util.UUID/randomUUID)
   :done false
   :task-name task-name})

(defn add-task
  "Given a file path, adds task to the end of the tasks list."
  [path task-name]
  (let [task (create-task task-name)
        task-id (:task-id task)
        state (db/read-content path)]

    (db/save! path (-> state
                       (update-in [:tasks-by-order] conj task-id)
                       (assoc-in [:tasks-by-id task-id] task)))))