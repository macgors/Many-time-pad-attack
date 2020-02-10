#  Attack on a reused One-Time Pad stream cipher. example.

Created for Cyber Security labs at WPPT, PWr

Task:
Przechwyciłaś/eś kilkana±cie kryptogramów. Wiesz, że każdy z nich powstał
jako rezultat szyfrowania wiadomo±ci za pomocą szyfru strumieniowego. Co więcej, do szyfrowania każdej wiadomości wykorzystano ten sam klucz, czyli: c_i = Enc(k, m_i) = m_i ⊕ G(k)
dla i = 1 . . . l, gdzie G jest generatorem bitów pseudolosowych, a k jest kluczem tajnym.
Napisz program, który przyjmuje l
kryptogramów zaszyfrowanych za pomocą szyfru strumieniowego z tym samym kluczem. Na
wyjściu program ma zwrócić teksty jawne.
