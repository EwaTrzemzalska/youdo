(ns youdo.core-test
  (:require [clojure.test :refer [is testing deftest]]
            [youdo.core :as core]))

(deftest proper-arguments-parsing
  (is (= (core/parse-args "add" ["-t" "Clean" "-p" "./house.youdo"])
         {:action "add" 
          :task "Clean" 
          :path "./house.youdo"}))
  (is (= (core/parse-args "list" ["-p" "./house.youdo"])
         {:action "list"
          :path "./house.youdo"})))

(deftest incorrect-arguments-cause-an-error
  (testing "User provides incorrect action"
    (is (thrown-with-msg? Exception #"Incorrect action"
                          (core/parse-args "print" []))))
  (testing "User doesn't provide an action"
    (is (thrown-with-msg? Exception #"Provide an action"
                          (core/parse-args "" []))))
  (testing "User doesn't provide a path"
    (is (thrown-with-msg? Exception #"Provide a path" 
                          (core/parse-args "list" []))))
  (testing "User doesn't provide a task" 
    (is (thrown-with-msg? Exception #"Provide a task" 
                          (core/parse-args 
                           "add" ["-p" "./house.youdo"]))))
  (testing "User provides a incorrect path"
    (is (thrown-with-msg? Exception #"Provide a correct path" 
                          (core/parse-args "add" ["-t " "Clean" "-p" "~Documents/check-weather.clj"])))))

