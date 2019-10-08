import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class servlet extends HttpServlet {
    private Users users=new Users();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PrintWriter out=resp.getWriter();
        out.write("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form>\n" +
                "    <input id=\"name\" name=\"name\"/>\n" +
                "\n" +
                "</form>\n" +
                "\n" +
                "<script>\n" +
                "    input=document.getElementById(\"name\");\n" +
                "    input.addEventListener(\"keypress\", function (ev) {\n" +
                "        if(ev.keyCode===13){\n" +
                "            ev.preventDefault();\n" +
                "           let xhr=new XMLHttpRequest();\n" +
                "           xhr.onreadystatechange=function(){\n" +
                "               if(this.readyState===4 && this.status===200) {\n" +
                "                   let resp=JSON.parse(this.response);\n" +
                "                   let tables=document.getElementsByTagName(\"table\");\n" +
                "                   \n" +
                "                   if(tables!=[]){\n" +
                "                       for(let table of tables)\n" +
                "                           table.outerHTML=\"\";\n" +
                "                   }\n" +
                "                   \n" +
                "                   \n" +
                "                   \n" +
                "                   let table=document.createElement(\"table\");\n" +
                "                   \n" +
                "                   let tr=document.createElement(\"tr\");\n" +
                "                   let th=document.createElement(\"th\");\n" +
                "                   th.innerText=\"Name\";\n" +
                "                   tr.append(th);\n" +
                "\n" +
                "                   th=document.createElement(\"th\");\n" +
                "                   th.innerText=\"Nationality\";\n" +
                "                   tr.append(th);\n" +
                "\n" +
                "                   th=document.createElement(\"th\");\n" +
                "                   th.innerText=\"Age\";\n" +
                "                   tr.append(th);\n" +
                "\n" +
                "                   table.append(tr);\n" +
                "                   for(let row of resp){\n" +
                "                       if(row.hasOwnProperty(\"fullName\") &&  row.hasOwnProperty(\"nationality\") && row.hasOwnProperty(\"age\")){\n" +
                "\n" +
                "                           tr=document.createElement(\"tr\");\n" +
                "                        let td=document.createElement(\"td\");\n" +
                "                        td.innerText=row.fullName;\n" +
                "                        tr.append(td);\n" +
                "\n" +
                "                       td=document.createElement(\"td\");\n" +
                "                       td.innerText=row.nationality;\n" +
                "                       tr.append(td);\n" +
                "\n" +
                "                       td=document.createElement(\"td\");\n" +
                "                       td.innerText=row.age;\n" +
                "                       tr.append(td);\n" +
                "\n" +
                "                        table.append(tr);}\n" +
                "                   }\n" +
                "                   document.body.append(table);\n" +
                "\n" +
                "               }\n" +
                "           }\n" +
                "           xhr.open(\"Post\",\"servlet\");\n" +
                "           xhr.send(input.value);\n" +
                "        }\n" +
                "    });\n" +
                "</script>\n" +
                "\n" +
                "</body>\n" +
                "</html>");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader input=req.getReader();
        String line=input.readLine();

        PrintWriter out= resp.getWriter();
        out.write(users.toJSON(users.search(line)));
    }
}
