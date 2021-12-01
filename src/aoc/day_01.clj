(ns aoc.day-01
  (:require [clojure.java.io :as io]))

(defn ->int [s]
  (Integer/parseInt s))

(defn count-times
  "Count the number of times a depth measurement increases from the previous measurement."
  [lines]
  (loop [depths (rest lines)
         previous (first lines)
         sum 0]
    (if (empty? depths)
      sum
      (let [current (first depths)
            next-sum (if (> current previous) (inc sum) sum)]
        (recur (rest depths) current next-sum)))))

(defn solution-1 [file-name]
  (with-open [reader (io/reader (io/resource file-name))]
    (->> (line-seq reader)
         (map ->int)
         (count-times))))


(defn count-sliding
  "Count the number of times the sum of measurements in this sliding window increases from the previous sum."
  [lines]
  (->> lines
       (map ->int)
       (partition-all 3 1)
       (map (partial apply +))
       (count-times)))

(defn solution-2 [file-name]
  (with-open [reader (io/reader (io/resource file-name))]
    (count-sliding (line-seq reader))))


(comment
  (solution-1 "day_01.txt");; => 1532
  (solution-2 "day_01.txt");; => 1571
)
