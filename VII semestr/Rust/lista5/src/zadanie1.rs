use std::collections::HashMap;

#[allow(dead_code)]
pub fn highlight(code: &str) -> String {
    let mut result = "".to_string(); 
    let mut prev_ch = '-';
    let mut span_close = "".to_string();
    
    let mut spans = HashMap::new();
    spans.insert(
        "F".to_string(),
        "<span style=\"color: pink\">".to_string(),
    );
    spans.insert(
        "L".to_string(),
        "<span style=\"color: red\">".to_string(),
    );
    spans.insert(
        "R".to_string(),
        "<span style=\"color: green\">".to_string(),
    );
    spans.insert(
        "0".to_string(),
        "<span style=\"color: orange\">".to_string(),
    );
    
    for mut ch in code.chars() {
        
        let old_ch = ch;
        if ch > '0' && ch <= '9' {
            ch = '0';
        }
        if ch == prev_ch {
            result+= &old_ch.to_string();
            continue;
        }
        result+=&span_close;
        result+= &spans.get(&ch.to_string()).unwrap_or(&"".to_string());
        result+= &old_ch.to_string();
        prev_ch=ch;
        if ch == ')' || ch == '(' {
            span_close = "".to_string();
            continue
        }
        span_close = "</span>".to_string();
        
    }
    result+= &span_close;
    result
  }




#[cfg(test)]
 mod tests {
      use super::*;
  
      #[cfg(test)]
      macro_rules! assert_highlight {
          ($code:expr , $expected:expr $(,)*) => {{
              let actual = highlight($code);
              let expected = $expected;
              println!("Code without syntax highlighting: {}", $code);
              println!("Your code with syntax highlighting: {}", actual);
              println!("Expected syntax highlighting: {}", expected);
              assert_eq!(actual, expected);
          }};
      }
  
      #[test]
      fn test1() {
          assert_highlight!(
              "F3RF5LF7",
              r#"<span style="color: pink">F</span><span style="color: orange">3</span><span style="color: green">R</span><span style="color: pink">F</span><span style="color: orange">5</span><span style="color: red">L</span><span style="color: pink">F</span><span style="color: orange">7</span>"#,
          );
      }
      #[test]
      fn test2() {
          assert_highlight!(
              "FFFR345F2LL",
              r#"<span style="color: pink">FFF</span><span style="color: green">R</span><span style="color: orange">345</span><span style="color: pink">F</span><span style="color: orange">2</span><span style="color: red">LL</span>"#,
          );
      }

      #[test]
      fn test3() {
          assert_highlight!(
              "69L21F38)R66(L32",
              "<span style=\"color: orange\">69</span><span style=\"color: red\">L</span><span style=\"color: orange\">21</span><span style=\"color: pink\">F</span><span style=\"color: orange\">38</span>)<span style=\"color: green\">R</span><span style=\"color: orange\">66</span>(<span style=\"color: red\">L</span><span style=\"color: orange\">32</span>",
          );
      }

      #[test]
      fn test4() {
          assert_highlight!(
              "69L21F38)R66(L3",
              "<span style=\"color: orange\">69</span><span style=\"color: red\">L</span><span style=\"color: orange\">21</span><span style=\"color: pink\">F</span><span style=\"color: orange\">38</span>)<span style=\"color: green\">R</span><span style=\"color: orange\">66</span>(<span style=\"color: red\">L</span><span style=\"color: orange\">3</span>",
          );
      }

      #[test]
      fn test5() {
          assert_highlight!(
              "69L21F38)(R66(L3",
              "<span style=\"color: orange\">69</span><span style=\"color: red\">L</span><span style=\"color: orange\">21</span><span style=\"color: pink\">F</span><span style=\"color: orange\">38</span>)(<span style=\"color: green\">R</span><span style=\"color: orange\">66</span>(<span style=\"color: red\">L</span><span style=\"color: orange\">3</span>",
          );
      }
  }