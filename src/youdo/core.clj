(ns youdo.core
  (:require [youdo.state :as state])
  (:gen-class))

(defn -main
  [& args]
  ;; dostajesz action, task i path
  (comment (if (= action "list") ;; TODO: Change to cond (conditional)
             (state/list-tasks path)
             (state/add-task path task))))
