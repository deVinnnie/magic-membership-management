package be.mira.jongeren.administration.config

import java.io.PrintWriter

import org.fusesource.scalate.RenderContext
import org.fusesource.scalate.servlet.ServletTemplateEngine

// Doesn't work.
class CustomServletTemplateEngine(override val config : org.fusesource.scalate.servlet.Config) extends ServletTemplateEngine(config){
  override def createRenderContext(uri: String, out: PrintWriter): RenderContext = {
    var renderContext = super.createRenderContext(uri, out)
    renderContext.numberFormat.setGroupingUsed(false)
    println("NUMBERFORMATTING: " + renderContext.numberFormat.isGroupingUsed)
    return renderContext
  }
}
