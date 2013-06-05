
object Test extends App {

	implicit class EnrichedList[Elem](val l: List[Elem]) extends AnyVal {
		def sort[T: math.Ordering](f: Elem => T): List[Elem] = l.sortBy(f)
		def number: List[(Int, Elem)] = l.zipWithIndex.map(_.swap).map(x => (x._1 + 1, x._2))
	}

	val words = List(
		"foo",
		"bar",
		"baz",
		"bippy",
		"bitdiddle",
		"ba",
		"the",
		"words",
		"x"
	)

	val numbers = List(1, 4, 6, 2)

	val a1 = for {
		word <- words
		then .to[Set]
	} yield word

	val a2 = for {
		word <- (
			for {
				word <- words
			} yield (word)
		).to[Set]
	} yield word

	assert(a1 == a2)

	val b1 = for {
		number <- numbers
		word <- words
		n <- then number
	} yield (word, number, n)

	val b2 = for {
		(n, (number, word)) <- (
			for {
				number <- numbers
				word <- words
			} yield (number, word)
		).number
	} yield (word, number, n)

	assert(b1 == b2)

	val c1 = for {
		x <- numbers
		y <- words
		x <- then number
	} yield (x, y)

	val c2 = for {
		(x, (_, y)) <- (
			for {
				x <- numbers
				y <- words
			} yield (x, y)
		).number
	} yield (x, y)

	assert(c1 == c2)

	val d1 = for {
		word <- words
		then sortBy with words.length
		x = word
		number <- numbers
		then .to[Set]
		then .to[Seq]
		then sortBy with number
		z = word + number
	} yield (word, number, z, x)

	val d2 = for {
		(word, x, number) <- (
			for {
				(word, x, number) <- (
					for {
						(word, x, number) <- (
							for {
								word <- (
									for {
										word <- words
									} yield word
								).sortBy { case word =>  words.length }
								x = word
								number <- numbers
							} yield (word, x, number)
						).to[Set]
					} yield (word, x, number)
				).to[Seq]
			} yield (word, x, number)
		).sortBy { case (word, x, number) => number }
		z = word + number
	} yield (word, number, z, x)

	assert(d1 == d2)

}

