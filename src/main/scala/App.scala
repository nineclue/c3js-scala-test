import org.scalajs.dom
import scala.scalajs.js
import js.annotation._
import js.JSConverters._

@js.native
@JSGlobal
class Chart() extends js.Object

@js.native
@JSGlobal("c3")
object C3 extends js.Object {
    def generate(data: js.Dynamic): js.Object = js.native
}

object App {
    // @JSGlobal
    def callback(d: js.Dynamic) = {
        println(d.value)
        println(d.id)
        println(d.i)
    }

    def c3test() = {
        /*
        var chart = c3.generate({
            bindto: '#chart1',
            data: {
            columns: [
                ['data1', 30, 200, 100, 400, 150, 250],
                ['data2', 50, 20, 10, 40, 15, 25]
            ]
            }
        });            
        */
        val d = js.Dynamic.literal(columns = 
            Seq(
                Seq.apply[Any]("자료1", 30, 200, 100, 400, 150, 250).toJSArray, 
                Seq.apply[Any]("자료2", 50, 0, 10, 0, 15, 25).toJSArray
            ).toJSArray,
            `type` = "bar",
            onmouseover = callback(_))
        // println(d)
        val axisOpt = js.Dynamic.literal(`type` = "category",
            categories = Seq("1월", "2월", "3월", "4월", "5월", "6월").toJSArray)
        val ax = js.Dynamic.literal(x = axisOpt)
        val config = js.Dynamic.literal(bindto = "#chart2", data = d, axis = ax)
        val c = C3.generate(config)
    }
    def main(as: Array[String]): Unit = {
        println("Starting...")
        val shead = dom.document.getElementById("hscala")
        shead.innerHTML = "스칼라.js C3"
        c3test()
    }

}