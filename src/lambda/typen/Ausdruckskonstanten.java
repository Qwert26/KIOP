package lambda.typen;
public enum Ausdruckskonstanten {
	AND {
			@Override
			public FunktionsTyp erhalteTyp() {
				return new FunktionsTyp(new Wahrheitswert(),new FunktionsTyp(new Wahrheitswert(),new Wahrheitswert()));
			}
		},
	OR {
			@Override
			public FunktionsTyp erhalteTyp() {
				return new FunktionsTyp(new Wahrheitswert(),new FunktionsTyp(new Wahrheitswert(),new Wahrheitswert()));
			}
		},
	NOT {
			@Override
			public FunktionsTyp erhalteTyp() {
				return new FunktionsTyp(new Wahrheitswert(),new Wahrheitswert());
			}
		},
	SUCC {
			@Override
			public FunktionsTyp erhalteTyp() {
				return new FunktionsTyp(new Zahl(),new Zahl());
			}
		},
	MULT {
			@Override
			public FunktionsTyp erhalteTyp() {
				return new FunktionsTyp(new Zahl(),new FunktionsTyp(new Zahl(),new Zahl()));
			}
		},
	PLUS {
			@Override
			public FunktionsTyp erhalteTyp() {
				return new FunktionsTyp(new Zahl(),new FunktionsTyp(new Zahl(),new Zahl()));
			}
		},
	TRUE {
			@Override
			public Typ erhalteTyp() {
				return new Wahrheitswert();
			}
		},
	FALSE {
			@Override
			public Typ erhalteTyp() {
				return new Wahrheitswert();
			}
		};
	public abstract Typ erhalteTyp();
}