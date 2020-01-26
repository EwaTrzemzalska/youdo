(ns youdo.db
  (:require [clojure.java.io :as io]))

(defn save!
  "Given a path, saves data"
  [path data])

(defn read!
  ""
  [path]
  (with-open [rdr (io/reader path)]
    (doall (line-seq rdr))))