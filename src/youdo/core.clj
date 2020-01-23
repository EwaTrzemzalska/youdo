(ns youdo.core
  (:require [youdo.state :as state]
            [clojure.tools.cli :as cli-tools])
  (:gen-class))

(def cli-options
  ;; An option with a required argument
  [["-t" "--task TASK" "Task description"]
   ["-p" "--path PATH" "Add path"
    :default "default-path"]
   ;; A boolean option defaulting to nil
   ["-h" "--help"]])

(defn parse-args [action args]
  (let [opts (cli-tools/parse-opts args cli-options)
        task (get-in opts [:options :task])
        path (get-in opts [:options :path])]
    (cond-> {:action action
             :path path}
      task (assoc :task task))))

(defn -main [action & args]
  (println (parse-args action args)))

