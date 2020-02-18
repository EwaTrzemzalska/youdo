(ns youdo.state
  (:require [youdo.db :as db]))

(defn list-tasks
  "Given a file path, returns list of tasks."
  [path]
  (db/read-content path))

(defn add-task
  "Given a file path, adds task to the end of the tasks list."
  [path task-name]
  (let [task {:done false :task-name task-name}]
    (db/save! path (conj (db/read-content path) task))))