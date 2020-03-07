include("bisec.jl")

function foo(x)
	x^2-2*x-atan(7x-2)
	end
	
println(funbis(0, 1, foo, 15))
println(funbis(2, 3, foo, 15))
