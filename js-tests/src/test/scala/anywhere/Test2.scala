package anywhere

import com.github.cuzfrog.IconvFacade
import com.github.cuzfrog.mytest.MyTestSuite

object Test2 extends MyTestSuite{
    test("iconv test"){
      val expectedText = "some text"
      val charset = "GBK"
      val bytes = IconvFacade.encode(expectedText, charset)
      val text = IconvFacade.decode(bytes, charset)
      assert(text == expectedText)
    }
}
