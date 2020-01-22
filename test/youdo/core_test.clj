(ns youdo.core-test
  (:require [clojure.test :refer :all]
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
  (is (thrown-with-msg? Exception #"Incorrect action" 
                        (core/parse-args "print" []))))
