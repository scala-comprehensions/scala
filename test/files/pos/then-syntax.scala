
object ThenSyntax {

	for {
		x <- 1 to 10
		then take 5
	} yield x
	
	for {
		x <- 1 to 10
		y <- 1 to 10
		then take 5
	} yield (x, y)

	for {
		x <- 1 to 10
		then take 5
		y <- 1 to 10
	} yield (x, y)

	for {
		x <- 1 to 10
		then .to[Seq]
		y <- 1 to 10
	} yield (x, y)

}
