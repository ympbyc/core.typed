(ns ^:skip-wiki clojure.core.typed.path-rep
  (:refer-clojure :exclude [defrecord defprotocol])
  (:require [clojure.core.typed.utils :as u]
            [clojure.core.typed :as t])
  (:import (clojure.lang Keyword)))

(t/tc-ignore
(alter-meta! *ns* assoc :skip-wiki true)
  )

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Paths

(t/ann-protocol IPathElem)
(u/defprotocol IPathElem)

(t/ann ^:no-check PathElem? (t/Pred IPathElem))
(defn PathElem? [a]
  (satisfies? IPathElem a))

(t/ann ^:no-check declare-path-elem [Class -> t/Any])
(defn declare-path-elem [c]
  (extend c IPathElem {}))

(t/ann-record NthPE [idx :- Number]) ;; More specific?
(u/def-object NthPE [idx]
  "A path accessing an indexed member, as by clojure.core/first, second, nth"
  [(integer? idx)
   (not (neg? idx))])

(t/ann-record NextPE [])
(u/def-object NextPE []
  "A path calling clojure.core/next"
  [])

(t/ann-record ClassPE [])
(u/def-object ClassPE []
  "A path calling clojure.core/class"
  [])

(t/ann-record CountPE [])
(u/def-object CountPE []
  "A path calling clojure.core/count"
  [])

(t/ann-record KeyPE [val :- Keyword])
(u/def-object KeyPE [val]
  "A key in a hash-map"
  [(keyword? val)])

(t/ann -kpe [Keyword -> KeyPE])
(def -kpe KeyPE-maker)

(t/ann-record KeysPE [])
(u/def-object KeysPE []
  "Calling clojure.core/keys"
  [])

(t/ann-record ValsPE [])
(u/def-object ValsPE []
  "Calling clojure.core/vals"
  [])

(declare-path-elem NthPE)
(declare-path-elem NextPE)
(declare-path-elem ClassPE)
(declare-path-elem CountPE)
(declare-path-elem KeyPE)
(declare-path-elem KeysPE)
(declare-path-elem ValsPE)

