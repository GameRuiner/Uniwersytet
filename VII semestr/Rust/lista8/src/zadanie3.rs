#[allow(dead_code)]
fn comp(a: Vec<i64>, b: Vec<i64>) -> bool {
    if a.len() != b.len() {
        return false;
    }
    //let mut a = a.clone();
    let mut b = b.clone();
    let mut a = a.clone().iter().map(|x| x*x).collect::<Vec<i64>>();
    a.sort();
    b.sort();
    //let mut res = true;
    //a.iter().zip(b).for_each(|(a, b)| if *a * *a!=b {res = false;});
    
    //return res;
    a == b
}

#[allow(dead_code)]
fn testing(a: Vec<i64>, b: Vec<i64>, exp: bool) -> () {
    assert_eq!(comp(a, b), exp)
}

#[test]
fn tests_comp1() {

    let a1 = vec![121, 144, 19, 161, 19, 144, 19, 11];
    let a2 = vec![11*11, 121*121, 144*144, 19*19, 161*161, 19*19, 144*144, 19*19];
    testing(a1, a2, true);
}

#[test]
fn tests_comp2() {
    let a1 = vec![121, 144, 19, 161, 19, 144, 19, 11];
    let a2 = vec![11*21, 121*121, 144*144, 19*19, 161*161, 19*19, 144*144, 19*19];
    testing(a1, a2, false);

}

#[test]
fn tests_comp3() {

    let a1 = vec![121, 144, 19, 161, 19, 144, 19, 11];
    let a2 = vec![11*11, 121*121, 144*144, 19*19, 161*161, 19*19, 144*144, 19*19];
    testing(a1, a2, true);
    let a1 = vec![121, 144, 19, 161, 19, 144, 19, 11];
    let a2 = vec![11*21, 121*121, 144*144, 19*19, 161*161, 19*19, 144*144, 19*19];
    testing(a1, a2, false);

}

#[test]
fn tests_comp4() {

    let a1 = vec![121, 144, 19, 161, 19, 144, 19];
    let a2 = vec![11*11, 121*121, 144*144, 19*19, 161*161, 19*19, 144*144, 19*19];
    testing(a1, a2, false);
}

#[test]
fn tests_comp5() {

    let a1 = vec![121, 144, 19, 161, 19, 144, 19, 11, 32];
    let a2 = vec![11*11, 121*121, 144*144, 19*19, 32*32, 161*161, 19*19, 144*144, 19*19];
    testing(a1, a2, true);

}