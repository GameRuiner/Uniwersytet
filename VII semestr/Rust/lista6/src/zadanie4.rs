#[allow(dead_code)]
fn xo(string: &'static str) -> bool {
    let acc = string.chars()
          .map(|x| x.to_ascii_lowercase())
          .fold((0,0), |mut acc, x| 
          {
              if x=='x' {
                acc.0+=1;
              } else if x == 'o' {
                acc.1+=1;
               }
            acc
          });

    acc.0 == acc.1
}

#[test]
fn test1() {
  assert_eq!(xo("xo"), true);
}

#[test]
fn test2() {
  assert_eq!(xo("Xo"), true);
}

#[test]
fn test3() {
  assert_eq!(xo("xxOo"), true);
}

#[test]
fn test4() {
  assert_eq!(xo("xxxm"), false);
}

#[test]
fn test5() {
  assert_eq!(xo("Oo"), false);
  assert_eq!(xo("ooom"), false);
}
