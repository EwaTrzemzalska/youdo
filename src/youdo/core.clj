(ns youdo.core
  (:require [clojure.tools.cli :as cli-tools]
            [clojure.string :as str]
            [youdo.state :as state])
  (:gen-class))

(defonce supported-actions #{"add" "list"})

(def cli-options
  [["-t" "--task TASK" "Task description"]
   ["-p" "--path PATH" "Add path"
    :default "default.youdo"]])

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
  (let [{:keys [action path task]} (parse-args action args)]
    (if (= action "add")
      (state/add-task path task)
      (state/list-tasks path))))

