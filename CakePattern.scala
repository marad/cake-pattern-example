case class Flour()
case class Bread(flour: Flour)

// Component definitions
trait MillComponent {
  def mill: Mill

  trait Mill {
    def produceFlour(): Flour
  }
}

trait BakeryComponent {
  def bakery: Bakery

  trait Bakery {
    def bakeBread(): Bread
  }
}

// Component implementations
trait RegularMillComponent extends MillComponent {
  val mill: Mill = new RegularMill

  class RegularMill extends Mill {
    def produceFlour() = Flour()
  }
}

trait RegularBakeryComponent extends BakeryComponent {
  this: MillComponent =>

  val bakery: Bakery = new RegularBakery

  class RegularBakery extends Bakery {
    def bakeBread() = Bread(mill.produceFlour())
  }
}

// Putting things together
object Application extends App {
  val app = new Object
    with RegularMillComponent
    with RegularBakeryComponent

  val bread = app.bakery.bakeBread()

  println(s"Baked bread: $bread")
}
