
package scala
package util
package internal

case class Identity[T](t: T) extends AnyVal {
	def map[U](f: Identity[T] => U) = f(this)
}

