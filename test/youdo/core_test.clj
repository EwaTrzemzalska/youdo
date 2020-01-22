(ns youdo.core-test
  (:require [clojure.test :refer :all]
            [youdo.core :as core]))

(deftest a-test
  (testing "First test"
   (is (= 1 1))))

(deftest proper-arguments-parsing
  (is (= (core/parse-args "add"
                          ["-t" "Clean" "-p" "./house.youdo"])
         {:options {:task "Clean", :path "./house.youdo"}
          :arguments [],
          :summary  "  -t, --task TASK  new task      Task description\n  -p, --path PATH  default-path  Add path\n  -h, --help"
          :errors nil})))

