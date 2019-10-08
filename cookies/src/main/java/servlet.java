import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.Buffer;

public class servlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out=resp.getWriter();
        out.write("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <form >\n" +
                "        <input name=\"search\" id=\"search\"/>\n" +
                "    </form>");
        Cookie [] cookies=req.getCookies();
        if(cookies!=null){

            for(Cookie cookie: cookies) {
                if (cookie.getName().equals("LastSearch")) {
                    out.write("<h1 id=\"result\">" + Data.search(cookie.getValue()) + "</h1>");
                    break;
                }
            }
        }

        out.write("<script>\n" +
                "    let input=document.getElementById(\"search\");\n" +
                "    input.addEventListener(\"keypress\",function (event) {\n" +
                "        if(event.keyCode===13) {\n" +
                "            event.preventDefault();\n" +
                "\n" +
                "            let xhr = new XMLHttpRequest();\n" +
                "\n" +
                "            xhr.onreadystatechange = function () {\n" +
                "                if (this.readyState === 4 && this.status === 200) {\n" +
                "                    let h = document.getElementById(\"result\");\n" +
                "                    if (h != null) h.outerHTML = \"\";\n" +
                "                    h = document.createElement(\"h1\");\n" +
                "                    h.innerText = this.response;\n" +
                "                    h.id = \"result\";\n" +
                "                    document.body.append(h);\n" +
                "                }\n" +
                "\n" +
                "            }\n" +
                "            xhr.open(\"POST\", \"/\");\n" +
                "            xhr.send(input.value);\n" +
                "        }\n" +
                "    });\n" +
                "\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out=resp.getWriter();
        BufferedReader input=req.getReader();
        String line=input.readLine();
        out.println(Data.search(line));
        Cookie cookie=new Cookie("LastSearch",line);
        cookie.setMaxAge(100000);
        resp.addCookie(cookie);
    }
}

