import org.scalajs.dom
import scala.scalajs.js
import js.annotation._
import js.JSConverters._
import com.raquo.laminar.api.L._
import com.github.uosis.laminar.webcomponents.material._
import com.github.uosis.laminar.webcomponents.material.{List => MWCList}

@js.native
@JSGlobal
class Chart() extends js.Object

@js.native
@JSGlobal("c3")
object C3 extends js.Object {
    def generate(data: js.Dynamic): js.Object = js.native
}

case class Summary(c1: String, c2: String, v: Int)

object App {
    // @JSGlobal
    def callback(d: js.Dynamic) = {
        println(d.id)
        println(d.index)
 
        // println(js.Object.properties(d))  : x, value, id, index, name
    }

    def c3bar() = {
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
            groups = Seq(Seq("자료1", "자료2").toJSArray).toJSArray,
            onclick = callback(_))
        // println(d)
        val axisOpt = js.Dynamic.literal(`type` = "category",
            categories = Seq("1월", "2월", "3월", "4월", "5월", "6월").toJSArray)
        val ax = js.Dynamic.literal(x = axisOpt)
        val config = js.Dynamic.literal(bindto = "#chart2", data = d, axis = ax)
        val c = C3.generate(config)
    }

    def c3donut() = {
        val labelFormat: (Double, Double, String) => String = 
            (value, ratio, id) => s"$id : $value(${(ratio * 100).round})"
        val tooltipTitleFormat: (Int) => String =
            (index) => s"검사($index)"
        val tooltipNameFormat: (String, Double, String, Int) => String = 
            // name == id ??
            (name, ratio, id, index) => s"$name - $id[$index]"
        val tooltipValueFormat: (Int, Double, String, Int) => String = 
            (name, ratio, id, index) => s"$name(${(ratio * 100).round}%)"
        val d = js.Dynamic.literal(columns = 
            Seq(
                Seq.apply[Any]("CT", 200).toJSArray,
                Seq.apply[Any]("US", 250).toJSArray,
                Seq.apply[Any]("MR", 77).toJSArray
            ).toJSArray,
            `type` = "donut",
            onclick = callback(_))
        val donut = js.Dynamic.literal(
            title = "멋진 도넛 차트",
            // label = js.Dynamic.literal(format = labelFormat)
            )
        val tooltip = js.Dynamic.literal(
            format = js.Dynamic.literal(
                // title = tooltipTitleFormat,
                // name = tooltipNameFormat,
                value = tooltipValueFormat
            ),
            // grouped = true
        )
        val config = js.Dynamic.literal(bindto ="#chart3", 
            data = d, donut = donut, tooltip = tooltip)
        val c = C3.generate(config)
    }

    def showlist() = {
        val sums = Seq(
            Summary("VW", "Beatle", 3),
            Summary("VW", "Polo", 5),
            Summary("BMW", "520d", 5),
            Summary("Merces Benz", "E300e", 7),
            Summary("Hyundai Genesis", "G70", 6),
            Summary("Hyundai", "Ionic5", 10)
        )
        val list = MWCList(
            // _.noninteractive(true),
            _.slots.default(
                sums.map(s => 
                    MWCList.ListItem(_.slots.default(span(s"${s.c1} - ${s.c2} : ${s.v}")))):_*
            )
        )
        render(dom.document.getElementById("list"), list)
    }

    def main(as: Array[String]): Unit = {
        println("Starting...")
        val shead = dom.document.getElementById("hscala")
        shead.innerHTML = "스칼라.js C3"
        c3bar()

        /* erased after c3 chart call
        val chart3 = dom.document.getElementById("chart3")
        val donutHead = dom.document.createElement("h2")
        donutHead.innerHTML = "스칼라.js Donut 차트"
        chart3.appendChild(donutHead)
        */
        c3donut()

        showlist()        
    }

}