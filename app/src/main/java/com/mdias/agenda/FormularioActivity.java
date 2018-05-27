package com.mdias.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mdias.agenda.dao.AlunoDAO;
import com.mdias.agenda.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        Intent intent = getIntent();
        Aluno aluno = (Aluno)  intent.getSerializableExtra("aluno");
        if (aluno !=null) {
            helper.preencheFormulario(aluno);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:

                Aluno aluno = helper.pegaAluno();
                AlunoDAO alunoDAO = new AlunoDAO(this);

                if (aluno.getId() != null)  {
                    alunoDAO.alterar(aluno);
                } else {
                    alunoDAO.inserir(aluno);
                }


                alunoDAO.close();

                Toast.makeText(
                        FormularioActivity.this,
                        String.format("Aluno %s salvo!", aluno.getNome()),
                        Toast.LENGTH_SHORT
                ).show();

                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
