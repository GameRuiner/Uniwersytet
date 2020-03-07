include("bisec.jl")

function foo(x)
	x*MathConstants.e^(-x)-0.06064
	end
	
# println(foo(0.5))
println(funbis(0, 1, foo, 15, 0.0646926359947960))
