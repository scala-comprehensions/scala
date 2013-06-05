
object WithSyntax {

	for {
		x <- 1 to 10
		then sortBy with x
	} yield x

	for {
		x <- 1 to 10
		then sortBy with x
		y <- 1 to 10
	} yield (x, y)

}
