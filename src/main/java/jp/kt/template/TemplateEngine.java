package jp.kt.template;

import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.log.NullLogChute;

/**
 * テンプレートとデータからマージしたテキストを生成するクラス.
 *
 * @author tatsuya.kumon
 */
public class TemplateEngine {
	/** テンプレート */
	private String template;

	/** VelocityContext */
	private VelocityContext context;

	static {
		// velocity.logの出力停止
		Properties p = new Properties();
		p.setProperty("runtime.log.logsystem.class",
				NullLogChute.class.getName());
		// Valocity初期化
		Velocity.init(p);
	}

	/**
	 * コンストラクタ.
	 *
	 * @param template
	 *            テンプレートテキスト
	 */
	public TemplateEngine(String template) {
		// テンプレートテキストのセット
		this.template = template;
		// VelocityContext生成
		this.context = new VelocityContext();
	}

	/**
	 * マージするデータをセット.
	 *
	 * @param key
	 *            テンプレートに記載のキー.
	 * @param value
	 *            キーにマッピングする値.StringでもListでもMapでもほぼ何でも可.
	 */
	public void putData(String key, Object value) {
		this.context.put(key, value);
	}

	/**
	 * マージする.
	 *
	 * @return マージされたテキスト
	 */
	public String merge() {
		StringWriter w = new StringWriter();
		Velocity.evaluate(context, w, "", template);
		String mergeText = w.toString();
		w.flush();
		return mergeText;
	}
}
