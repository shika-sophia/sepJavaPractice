package javaPractice.chapter05;

public class CmdLineArgs {
	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
	}
}
/*
＊Eclipseでのコマンドライン実行方法
ファイル => 実行 => 実行の構成 => 検索or参照 => そのファイルを選択
=> 引数タブ => コマンドライン引数を入力 => 実行
*/