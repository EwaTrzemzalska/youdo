(ns youdo.db
  (:require [clojure.java.io :as io]))

(defn save!
  "Given a path, replaces contents of the file with the given data. 
   If the file doesn't exist it will be created under the given path."
  [path data]
  (spit path data))

(defn read!
  "Given a path returns the content of the file."
  [path]
  (slurp path))
