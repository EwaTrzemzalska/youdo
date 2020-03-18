(ns youdo.state
  (:require [youdo.db :as db]))

(defn list-tasks
  "Given a file path, returns list of tasks."
  [path]
  (map :task-name (db/read-content path)))

(defn create-task
  [task-name]
  {:done false :task-name task-name})


(defn add-task
  "Given a file path, adds task to the end of the tasks list."
  [path task-name]
  (let [task (create-task task-name)]

    (db/save! path (conj (db/read-content path) task))))
