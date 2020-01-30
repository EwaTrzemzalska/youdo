(ns youdo.db
  (:require [clojure.java.io :as io]))

(defn save!
  "Given a path, replaces contents of the file with the given data. 
   If the file doesn't exist it will be created under given path."
  [path data]
  (spit path data))

(defn read-content
  "Given a path returns a content of the file."
  [path]
  (slurp path))