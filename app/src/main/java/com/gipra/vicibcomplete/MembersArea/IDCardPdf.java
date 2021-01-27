package com.gipra.vicibcomplete.MembersArea;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.gipra.vicibcomplete.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;

import java.io.File;
import java.util.List;

public class IDCardPdf extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener{
    PDFView pdfView;
    Integer pageNumber = 0;
    String pdfFileName;
    String TAG="PDFViewActivity";
    int position=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_d_card_pdf);
        init();
    }
    private void init(){
        pdfView= (PDFView)findViewById(R.id.pdfview);
        position = getIntent().getIntExtra("position",-1);
        displayFromSdcard();
    }
    private void displayFromSdcard() {
        //Do not hardcode "/sdcard/"; use Environment.getExternalStorageDirectory().getPath() insteadpdfFileName==
//         pdfFileName= Environment.getExternalStorageDirectory().getPath();
//        pdfFileName ="/sdcard/Test.pdf";
//        File file = new File(pdfFileName);
//        java.io.File xmlFile = new java.io.File((getApplicationContext()
//                .getApplicationContext().getFileStreamPath("FileName.xml")
//                .getPath()));

        java.io.File xmlFile = new java.io.File(Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                + "/Filename.xml");

        Log.e("File path",xmlFile.getAbsolutePath());
        pdfView.fromFile(xmlFile)
                .defaultPage(pageNumber)
                .enableSwipe(true)

                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }
    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }
    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), "-");

    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }
}