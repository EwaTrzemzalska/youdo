(ns youdo.db
  (:require [clojure.edn :as edn]))

(defn save!
  "Given a path, replaces contents of the file with the given data. 
   If the file doesn't exist it will be created under the given path."
  [path data]
  (spit path data))

(defn read-content
  "Given a path returns the content of the file."
  [path]
  (edn/read-string (slurp path)))