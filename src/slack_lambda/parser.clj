(ns slack-lambda.parser
  (:require [clojure.string :as string]
            [instaparse.core :as insta]))

(def parser
  (insta/parser
   "<dialogue> = <'bot '> (say-hi | say-goodbye)
    say-hi = <'hello '> person
    say-goodbye = <'goodbye '> person
    person = 'world' | 'jean-louis' | 'suvash'"))

(defn- cleanup [text]
  (-> text
      string/lower-case
      (string/replace #"[,.!?:@]" " ")
      (string/replace "’" "'")
      (string/replace #"[\s]+" " ")
      string/trim))

(defn success? [result]
  (not (insta/failure? result)))

(defn failure->string [failure]
  (with-out-str (instaparse.failure/print-reason failure)))

(defn normalize [[action & args]]
  [action (into {} args)])

(defn parse [text]
  (let [result (insta/parse parser (cleanup text))]
    (if (success? result)
      (normalize (first result))
      result)))
