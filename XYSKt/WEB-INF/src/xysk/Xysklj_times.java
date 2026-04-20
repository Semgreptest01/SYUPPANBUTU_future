/*
 *
 *  修正履歴
 *  L001  2006/05/12 K.Sasaki   ログ出力対応　ログ出力メソッドの呼び出し追加
 *
 */
package xysk;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 非同期処理用クラス
 */
public class Xysklj_times extends Xysklj_ajax {

	public String business(HttpServletRequest	req,
			HttpServletResponse	res) throws Exception {
	
		String ret = "9:";
		String mode = req.getParameter("mode");
		
		// 時間取得
		if ("time".equals(mode)) {
			ret = "0:" + Xysflj_Common.curr_Date() + " " + Xysflj_Common.curr_Time();


		}
		System.out.println("mode=" + mode + ",ret=" + ret);
			

		// 結果出力
		return ret;
		
	}

}
