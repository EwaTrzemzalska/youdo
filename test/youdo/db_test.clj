(ns youdo.db-test
  (:require [youdo.db :as db]
            [clojure.test :refer [is deftest]]
            [clojure.java.io :as io]))

(def test-file-1 (io/resource "test/tasks-list.youdo"))
(def test-action "Laundry")

(def random-file-name (str "test/" (.toString (java.util.UUID/randomUUID)) ".youdo"))


(deftest reading-file-content
  (is (= (db/read! test-file-1) 
         "Clean the kitchen\nWash dishes\nDo groceries")))

(deftest saving-file-with-data
  (let [test-file-2 random-file-name]
    (db/save! test-file-2 test-action)
    (is (= (slurp test-file-2) test-action))
    (io/delete-file test-file-2)))


; (deftest face-with-exeptions-when-folder-doesnt-exist)