package com.example.demo.app;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//userの入力値を取得して、htmlの表示などのを命令する
@Controller 
//ドメイン以降のURL
@RequestMapping("/sample")
public class SampleController {
	
	//jdbcでデータベースを操作するためのクラス
	private final JdbcTemplate jdbcTemplate;
	
	//Dependency Injection インスタンス化されたオブジェクトを自動で渡してくれる機能
	//裏でDIコンテナというクラスが働いている
	@Autowired
	public SampleController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//sample/testでアクセスできるようになる(Requestメゾットがgetの場合)
	@GetMapping("/test")
	public String test(Model model) {
		
		String sql = "SELECT id, name, email "
				+"FROM inquiry WHERE id = 1";
		//Mapのstringがカラム名
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		
		model.addAttribute("title", "Inquiry Form");
		model.addAttribute("name",map.get("name"));
		model.addAttribute("email",map.get("email"));
		
		//testの後ろに.htmlがつけられる。returnの後viewクラスが必要なデータを受けこみながら、test.htmlをレンダリングする。
		return "test";
	}

}
