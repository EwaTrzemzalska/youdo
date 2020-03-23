(ns youdo.state
  (:require [youdo.db :as db]))

(defn list-tasks
  "Given a file path, returns list of tasks."
  [path]
  (map :task-name (db/read-content path)))

(defn create-task
  [task-name]
    
  [(java.util.UUID/randomUUID) {:done false 
                                :task-name task-name}])






(defn add-task
  "Given a file path, adds task to the end of the tasks list."
  [path task-name]
  (let [task (create-task task-name)
        task-uuid (first task)
        task-values (second task)
        vector-with-uuids (get (db/read-content path) :tasks-by-order)
        map-of-tasks-by-id (get (db/read-content path) :tasks-by-id)]
    
    
    (conj vector-with-uuids task-uuid)
    ;; conjoin vector from :tasks-by-order with uuid of new task
    
    (assoc map-of-tasks-by-id task-uuid task-values)
    
    
    (db/save! path (conj (db/read-content path) task))))






{:tasks-by-order [#uuid"5d77ca9f-0ceb-4ff1-a3b3-0b0c4dd4a489"]
 :tasks-by-id {"5d77ca9f-0ceb-4ff1-a3b3-0b0c4dd4a489" {:task-id "5d77ca9f-0ceb-4ff1-a3b3-0b0c4dd4a489"
                                                       :done false
                                                       :task-name "Do laundry"}
               "954b8017-07c7-48df-8687-ab16092a2833" {:done false
                                                       :task-name "Wash dishes"}}}


