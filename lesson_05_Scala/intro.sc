val firstName: String = "Till"
val secondName: String = "Lindemann"
val age: Int = 45
val weight = 85.6f
// println(firstName, secondName, age, weight)

case class Person(firstName: String,
                  secondName: String,
                  age: Int,
                  weight: Double)

val vocal = Person(firstName, secondName, age, weight)
println(vocal.age)

var gitarist = Person("Andrey", "Belov", 23, 68)

s"Greetings ${vocal.firstName}!"

if (vocal.age < 50 & vocal.firstName != "Till") {
  println("Yang man")
} else {
  println("Hello experienced man!")
}

val band: Array[Person] = Array(vocal, gitarist)

val list = Array(1, 2, 3)
for (i <- list) {
  println(i)
}

for (i <- 10 to 14 by 2) {
  print(i)
}

list.map(el => s"It's ${el}")

list.filter(el => el>=2)

list.reduce(_+_)
