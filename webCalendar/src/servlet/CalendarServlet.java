/**
 * @title webCalendar / servlet / CalendarServlet.java
 * @content カレンダーの表示 / 年月入力 / 日付クリック / 日別メモの入力・表示
 *
 * @package ---- servlet ----
 * @class CalendarServlet //◆Srart Point / カレンダーの表示 / 年月の入力処理
 * @class FunctionServlet //Prev,Nextボタンの処理 / 日付のクリック処理
 * @class MemoServlet     //両Servletからの最終処理 / memoの表示 / calendarViewへforward
 * @class WebCalendarFilter //request.setCharacterEncoding("UTF-8")のみ。全Servlet/jspに適用
 *
 * @package ---- model ----
 * @class CalendarLogic   //カレンダーの日付List作成 / データの保持(Beans風)
 * @class InputLogic      //年月入力の不正値を判定
 * @class MemoLogic       //memo関連のDAO呼び出し / memoの表示
 * @class Message         //表示メッセージのswitch / 文字列
 *
 * @package ---- dao ----
 * @class DataAccess      //各DAOメソッド / 日付の整形
 *
 * @package ---- WebContent ----
 * @page /WEB-INF/webCalendar/calendarView.jsp   //全テーブルの表示 <jsp:include>の呼出元
 * @page /WEB-INF/webCalendar/calendarHeader.jsp //headerのタイトル表示
 * @page /WEB-INF/webCalendar/calendarTable.jsp  //カレンダーのテーブル作成(今月/先月/来月で３回利用)
 * @page /WEB-INF/webCalendar/calendarInput.jsp  //日付入力<form> / Prev,Nextボタン
 * @page /WEB-INF/webCalendar/calendarMemo.jsp   //メモの入力/表示/削除
 *
 * @package ---- StyleSheet ----
 * @page /css/webCalendarStyle.css //webCalendarのスタイルシート
 * @page /images/xxxxx.jpg         //季節感に合わせた画像を表示(未実装)
 *
 * @package  ---- reference ----
 * @page webCalendarDB_Setting.txt //データベース設定
 * @page /reference/image/xxxx.jpg //実行画面の画像
 *
 * @author shika
 * @date 2020-12-08 ～ 2021-01-03
 */

package servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CalendarLogic;
import model.InputLogic;
import model.Message;


@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Message mess;
    private InputLogic inLogic;
    private CalendarLogic calen;
    private MemoServlet memoServlet;
    private HttpSession session;

    //====== ◆Start Point ======
    //------ initialize ------
    public void init(ServletConfig config) throws ServletException {
        mess = new Message();
        inLogic = new InputLogic();
        calen = new CalendarLogic();
        memoServlet = new MemoServlet();
    }//init()

    //====== initial calendar ======
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session = request.getSession();
        mess.msgForInput();
        calen.dateNow();

        withMemo(request, response);
    }//doGet()


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String yearStr = request.getParameter("inputYear");
        String monthStr = request.getParameter("inputMonth");

        //---- 不正値チェック(非整数)----
        boolean isMatch = inLogic.transInt(mess, yearStr, monthStr);

        int year = 0;
        int month = 0;

        if(isMatch) {
            //---- 不正値チェック(範囲外) ----
            isMatch = inLogic.inputMatch(mess);

            year = inLogic.getYear();
            month = inLogic.getMonth();
        }

        if(isMatch) {
            calen.dateInput(year, month);
        } else {
            calen.dateNow();
        }

        withMemo(request, response);
    }//doPost()


    private void withMemo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //---- necessary setting ----
        session.setAttribute("mess", mess);
        session.setAttribute("calen", calen);

        //---- MemoServlet ----
        memoServlet.doGet(request, response);
    }//withMemo()

}//class
