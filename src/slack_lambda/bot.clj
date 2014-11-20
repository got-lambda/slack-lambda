(ns slack-lambda.bot
  (:require [clojure.string :as string]
            [slack-lambda.parser :as parser]))

(defn thanks? [text]
  (re-matches #"(?i).*(thank|thx|tack).*" text))

(defmulti react first)

(defmethod react :say-hi
  [[_ {:keys [person]}]]
  (str "Good day to you, " person))

(defmethod react :say-goodbye
  [[_ {:keys [person]}]]
  (str "Goodbye to you, " person))

(defn reply [user-name text]
  (let [parse-result (parser/parse text)]
    (cond
     (parser/success? parse-result) (react parse-result)
     (thanks? text) (str "you're welcome, @" user-name)
     :else (str user-name ": does not compute:\n"
                "```\n"
                (parser/failure->string parse-result)
                "\n```"))))
