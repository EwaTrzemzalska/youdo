(ns youdo.state
  (:require [youdo.db :as db]))

(defn list-tasks
  "Given a file path, returns list of tasks."
  [path]
  (db/read-content path))

(defn add-task
  "Given a file path, adds task to the end of the tasks list."
  [path task]
  (db/save! path task))