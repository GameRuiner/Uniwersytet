#[allow(dead_code)]
pub fn zoom(n: i32) -> String {
    let first;
    
    if (n+1)/2%2 != 0 {
        first = "■";
    } else {
        first = "□";       
    }

    if n == 1 {
        return  "■".to_string();
    } 

    let mut line = "".to_string();

    let mut res = zoom(n -2);

    let res_split = res.clone();

    let mut res_add = "".to_string();

    for ln in res_split.split("\n") {
        //let mut str_ln = ln.to_string(); 

            let mut temp = first.to_string();
            temp.push_str(&ln);
            let mut str_ln = temp;
            str_ln.push_str(&first.to_string());

        str_ln.push_str(&"\n".to_string());
        res_add.push_str(&str_ln);
}

    res = res_add;


    for _i in 0..n {
        line.push_str(&first.to_string());
    }
    line.push_str(&"\n".to_string());

    line.push_str(&res);

    res = line;
    
    let mut line = "".to_string();

    for _i in 0..n {
        line.push_str(&first.to_string());
    }


    res.push_str(&line);

    res

}



#[test]
fn basic_test_1() {
  assert_eq!(zoom(1), "■");
}

#[test]
fn basic_test_2() {
  assert_eq!(zoom(3), "\
□□□
□■□
□□□"
  );
}

#[test]
fn basic_test_3() {
  assert_eq!(zoom(5), "\
■■■■■
■□□□■
■□■□■
■□□□■
■■■■■"
  );
}

#[test]
fn basic_test_4() {
  assert_eq!(zoom(7), "\
□□□□□□□
□■■■■■□
□■□□□■□
□■□■□■□
□■□□□■□
□■■■■■□
□□□□□□□"
  );
}

#[test]
fn basic_test_5() {
  assert_eq!(zoom(9), "\
■■■■■■■■■
■□□□□□□□■
■□■■■■■□■
■□■□□□■□■
■□■□■□■□■
■□■□□□■□■
■□■■■■■□■
■□□□□□□□■
■■■■■■■■■"
  );
}