(ns youdo.state
  (:require [youdo.db :as db]))

(defn list-tasks
  [path]
  (let [content (db/read-content path)
        tasks-by-order (:tasks-by-order content)]
    (println (map #(get-in content [:tasks-by-id % :task-name]) tasks-by-order))))

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
                       (update :tasks-by-order conj task-id)
                       (assoc-in [:tasks-by-id task-id] task)))))