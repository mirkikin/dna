package com.marco.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class DnaService implements IDnaService {

	public boolean hasMutation(String[] dna) {
		Dna entity = new Dna();
		IDnaDAO dao = new DnaDAO();
		List<String> rows = Arrays.asList(dna).stream().map(actual -> actual.replace(" ", "").trim())
				.collect(Collectors.toList());
		entity.setSequence(StringUtils.join(rows, ", "));
		long countOnRows = this.countMutationsOnString(rows);
		if (countOnRows > 0) {
			entity.setMutation(true);
			dao.saveDnaTest(entity);
			return true;
		}
		countOnRows = this.countMutationsOnString(this.convertColumnsToString(rows.toArray(new String[dna.length])));
		if (countOnRows > 0) {
			entity.setMutation(true);
			dao.saveDnaTest(entity);
			return true;
		}
		countOnRows = this.countMutationsOnString(this.convertDiagonalToString(rows.toArray(new String[dna.length])));
		if (countOnRows > 0) {
			entity.setMutation(true);
			dao.saveDnaTest(entity);
			return true;
		}
		entity.setMutation(false);
		dao.saveDnaTest(entity);
		return false;
	}

	public Stats getStats() {
		IDnaDAO dao = new DnaDAO();
		List<Dna> dnas = dao.getStats();
		long noMutations = dnas.stream().filter(d -> !d.getMutation()).collect(Collectors.counting());
		long mutations = dnas.stream().filter(d -> d.getMutation()).collect(Collectors.counting());
		double ratio = 0.0d;
		if (noMutations > 0) {
			ratio = (double)mutations / (double)noMutations;
		}
		Stats stats = new Stats();
		stats.setCountMutations(mutations);
		stats.setCountNoMutations(noMutations);
		stats.setRatio(ratio);

		return stats;
	}

	private long countMutationsOnString(List<String> toCompare) {
		List<Long> counts = toCompare.stream().map(row -> {
			Map<String, Long> groups = row.chars().mapToObj(c -> (char) c)
					.collect(Collectors.groupingBy(Object::toString, Collectors.counting()));
			List<String> repeatedChars = groups.entrySet().stream().filter(actual -> actual.getValue() >= 4)
					.map(entry -> entry.getKey()).collect(Collectors.toList());
			long toReturn = 0;
			for (String actualChar : repeatedChars) {
				String filled = StringUtils.repeat(actualChar, 4);
				toReturn += row.contains(filled) ? 1 : 0;
			}

			return toReturn;
		}).filter(count -> count > 0).collect(Collectors.toList());

		return counts.size();

	}

	private List<String> convertColumnsToString(String[] rows) {
		List<String> res = new ArrayList<>();
		for (int x = 0; x < rows.length; x++) {
			StringBuilder builder = new StringBuilder();
			for (int y = 0; y < rows.length; y++) {
				builder.append(rows[y].charAt(x));
			}
			res.add(builder.toString());
		}
		return res;
	}

	private List<String> convertDiagonalToString(String[] rows) {
		List<String> res = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		for (int x = 0; x < rows.length; x++) {
			builder.append(rows[x].charAt(x));
		}
		builder = new StringBuilder();
		for (int x = (rows.length - 1); x >= 0; x--) {
			builder.append(rows[x].charAt(x));
		}
		res.add(builder.toString());
		return res;
	}

}
