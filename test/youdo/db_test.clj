(ns youdo.db-test
  (:require [youdo.db :as db]
            [clojure.test :refer [is deftest]]
            [clojure.java.io :as io]))

(def test-file (io/resource "test/tasks-list.youdo"))
(def test-action "Laundry")



(deftest reading-file-content
  (is (= (db/read! test-file) 
         "Clean the kitchen\nWash dishes\nDo groceries")))

(deftest saving-file-with-data
  (let [random-file-path (str "test/" (.toString (java.util.UUID/randomUUID)) ".youdo")]
    (db/save! random-file-path test-action)
    (is (= (slurp random-file-path) test-action))
    (io/delete-file random-file-path)))


; (deftest face-with-exeptions-when-folder-doesnt-exist)