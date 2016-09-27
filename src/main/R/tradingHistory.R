trades <- read.csv(file="./NASDAQ_20120927.txt", header=TRUE, sep=",")

is.data.frame(trades)

is.list(trades)

sum(trades$open)

max(trades$open)

min(trades$open)

mean(trades$open)

as.data.frame(trades)

tradesUnder3 = subset(trades, open <= .03)

tradesUnder1MaxVol = subset(trades, open <= 1 & vol >= 1e+6)