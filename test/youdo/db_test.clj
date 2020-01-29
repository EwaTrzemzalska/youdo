(ns youdo.db-test
  (:require [youdo.db :as db]
            [clojure.test :refer [is deftest]]
            [clojure.java.io :as io]
            [clj-http.client :as client]))

(def test-file-1 (io/resource "test/tasks-list.youdo"))
(def test-action "Laundry")

(def endpoint "https://www.uuidgenerator.net/api/version1")
(defn get-uuid [endpoint] (-> (client/get endpoint)
                              :body))
(def test-file-2 (str "test/" (get-uuid endpoint) ".youdo"))


(deftest reading-file-content
  (is (= (db/read! test-file-1) 
         "Clean the kitchen\nWash dishes\nDo groceries")))

(deftest saving-file-with-data
  (let [test-file-2 (str "test/" (get-uuid endpoint) ".youdo")]
    (db/save! test-file-2 test-action)
    (is (= (slurp test-file-2) test-action))
    (io/delete-file test-file-2)))


; (deftest face-with-exeptions-when-folder-doesnt-exist)