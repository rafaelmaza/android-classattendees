package com.mazariolli.android.classattendees.dao;

import java.util.List;

import org.json.JSONException;
import org.json.JSONStringer;

public class AttendeeConverter {
	public String toJSON(List<Attendee> attendees) {

		try {
			JSONStringer jsonStringer = new JSONStringer();
			jsonStringer.object().key("list").array().object().key("aluno")
					.array();

			for (Attendee attendee : attendees) {
				jsonStringer.object().key("id").value(attendee.getId())
						.key("nome").value(attendee.getName()).key("telefone")
						.value(attendee.getPhone()).key("endereco")
						.value(attendee.getAddress()).key("site")
						.value(attendee.getWebsite()).key("nota")
						.value(attendee.getScore()).endObject();
			}

			return jsonStringer.endArray().endObject().endArray().endObject()
					.toString();

		} catch (JSONException e) {
			throw new RuntimeException(e);
		}

	}
}
