(ns youdo.state-test
  (:require [youdo.state :as state]
            [clojure.test :refer [is deftest]]
            [clojure.java.io :as io]))

(def test-file-1 (io/resource "test/tasks-list.youdo"))

(deftest listing
  (is (= (state/list-tasks test-file-1) 
         ["Clean the kitchen" "Wash dishes" "Do groceries"])))

