include("gamma.jl")
include("romberg.jl")
using Plots


for n in [1,10,30]
	coeff = gamma((n+1)/2)/(sqrt(n*pi)*gamma(n/2))
	function f(x)
	(1 + (x^2)/n)^(-(n+1)/2)
   end
	
	x = Array{Float64}(undef,30)
   y = Array{Float64}(undef,30)
	for i in 1:30
		x[i] = i/3
		y[i] = 2*coeff*(romberga(0,x[i],f))
	end
	p = plot(x,y)
   display(p)
	re = readline()
end