(ns youdo.core
  (:require [youdo.state :as state]
            [clojure.tools.cli :as cli-tools]
            [clojure.string :as str])
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
        task (get-in opts [:options :task])]
    
    (case action
      "add" (case task 
             nil (throw (ex-info "Task not found"
                              {:some "data that helps debug problem, for example arguments that were passsed to the function"}))
              (cond-> {:action (str/lower-case action)
                       :path (get-in opts [:options :path])}
                task (assoc :task task)))
      "list" (cond-> {:action (str/lower-case action)
                      :path (get-in opts [:options :path])}
               task (assoc :task task))
      "" (throw (ex-info "Action not found"
                         {:some "data that helps debug problem, for example arguments that were passsed to the function"}))
      (throw (ex-info "Incorrect action"
                      {:some "data that helps debug problem, for example arguments that were passsed to the function"})))))

(defn -main [action & args]
  (println (parse-args action args)))

; ((str/lower-case action)
 
;  (if (= action "")
;    (throw (ex-info "Explain the error to the other developer. This is what will show up in the terminal, not to the user"
;                    {:some "data that helps debug problem, for example arguments that were passsed to the function"})))
;  )