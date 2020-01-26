(ns youdo.core
  (:require [youdo.state :as state]
            [clojure.tools.cli :as cli-tools]
            [clojure.string :as str])
  (:gen-class))

(defonce supported-actions #{"add" "list"})

(def cli-options
  [["-t" "--task TASK" "Task description"]
   ["-p" "--path PATH" "Add path"
    :default "default-path"]])

(defn parse-args [action args]
  (let [opts (cli-tools/parse-opts args cli-options)
        task (get-in opts [:options :task])]

    (assert (not (str/blank? action)) "Action not found")
    (assert (not (and (= action "add") (nil? task))) "Task not found")
    (assert (supported-actions action) "Incorrect action")

    (cond-> {:action (str/lower-case action)
             :path (get-in opts [:options :path])}
      task (assoc :task task))))

(defn -main [action & args]
  (println (parse-args action args)))
